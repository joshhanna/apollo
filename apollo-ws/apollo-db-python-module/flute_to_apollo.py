import os, sys, string
import math
import optparse
import apollo
import time
import glob, hashlib
import datetime
import random
from multiprocessing import Process, Queue

def parseFips(state, county, tract):
    fipsString = ""
    if len(str(state)) == 1:
        fipsString = "0" + str(state)
    else:
        fipsString = str(state)
    
    if len(str(county)) == 1:
        fipsString += "00" + str(county)
    elif len(str(county)) == 2:
        fipsString += "0" + str(county)
    else:
        fipsString += str(county)
        
    fipsString += str(tract)
    return fipsString

def statusUpdate(status, message):
    statusFile = './starttime'
    with open(statusFile, "wb") as f:
        f.write("%s %s" % (status, message))

def error_exit(message):
	# statusUpdate("LOGERROR",message)
	sys.stderr.write(message)
	sys.exit(1)

if __name__ == '__main__':
    parser = optparse.OptionParser(usage="""
    %prog [--help]
    """)
    
    parser.add_option("-d", "--debug", action="store_true")
    parser.add_option("-i", "--runId", type="string",
		      help="The Apollo RunId for this call")
    
    parser.add_option("-H", "--dbHost", type="string", default="warhol-fred.psc.edu")
    parser.add_option("-D", "--dbName", type="string", default="test")
    parser.add_option("-U", "--dbUser", type="string", default="apolloint")
    parser.add_option("-P", "--dbPword", type="string", default="int4p0llo")
    parser.add_option("-n", "--no_db", action="store_true", default=False, 
                      help='(Debugging) Flag prevents from writing to the database, but still need database to run')

    opts, args = parser.parse_args()
    
    try:
        apolloDB = apollo.ApolloDB(host_=opts.dbHost,
                                   dbname_=opts.dbName,
                                   user_=opts.dbUser,
                                   password_=opts.dbPword)

        apolloDB.connect()
        
    except Exception as e:
        error_exit(str(e))
    
    tractList = []
    simulationRunId = opts.runId

    # ## Parse the Tracts that you need to know
    try:
        with open("Tracts0", "rb") as f:
            for line in f.readlines()[1:]:
                lineSplit = line.split(",")
                tractList.append(parseFips(lineSplit[1], lineSplit[2], lineSplit[3]))
    except Exception as e:
        error_exit(str(e))
                               
                
    try:
        #### Gather op the list of Log files
        logFiles = glob.glob('Log*')

        
        # # First find the number of days that this job was run for
        numberOfDays = 0
        for logFile in logFiles:
            with open(logFile, "rb") as f:
            # # get the last line
                lastLineSplit = f.readlines()[-1].split(",")
                if int(lastLineSplit[0]) > numberOfDays: numberOfDays = int(lastLineSplit[0])
                
        numberOfDays += 1
        newInfectsPerDayPerTract = [[0.0 for x in range(numberOfDays)] for x in range(len(tractList))]
        
        for logFile in logFiles:
            
            with open(logFile, 'rb') as f:
                # ## skip Header
                isHead = False
                for line in f:
                    if not isHead:
                        isHead = True
                    else:
                        lineSplit = line.split(",")
                        newInfectsVal = sum(float(x) for x in lineSplit[12:16])
                        # print lineSplit[1] +" " + lineSplit[0]
                        newInfectsPerDayPerTract[int(lineSplit[1])][int(lineSplit[0])] += newInfectsVal
            
        # ## Now make them an average
        for i in range(0, len(newInfectsPerDayPerTract)):
            for j in range(0, len(newInfectsPerDayPerTract[i])):
                newInfectsPerDayPerTract[i][j] /= float(len(logFiles))
                        
        
        # ## Create the output string
        outStrList = []
        for i in range(0, len(newInfectsPerDayPerTract)):
            for j in range(0, len(newInfectsPerDayPerTract[i])):
                outStrList.append('US%s %d %d:1\n' % (tractList[i], int(math.ceil(newInfectsPerDayPerTract[i][j])), j))
        
        
        outStr = ''.join(outStrList)
    	with open('out.log', 'wb') as f:
    	    f.write(outStr)
        
        m = hashlib.md5()
        m.update(outStr)
        m5hash = m.hexdigest() 
        bufferSize = len(outStr)
        count = 0
        runDataContentId = -1
        if opts.no_db is False:
            hashvar = apolloDB.checkMD5HashExistence(m5hash)
            if hashvar > -1:
                runDataContentId = hashvar
            else:
                SQLString = 'INSERT INTO run_data_content (text_content, md5_hash_of_content) values ("%s","%s")' % (outStr, m5hash)
                apolloDB.query(SQLString)
                runDataContentId = apolloDB.insertID()
        softIdTS = apolloDB.getSoftwareIdentificationId(name_="Time Series Visualizer", version_="1.0")
        softIdGAIA = apolloDB.getSoftwareIdentificationId(name_="GAIA", version_="1.0")
        softIdFLUTE = apolloDB.getSoftwareIdentificationId(name_="FluTE", version_="1.15")
        runDataDescriptionIdTS = apolloDB.getRunDataDescriptionId(label_="newly_exposed.txt",
                                                                    source_software_=softIdFLUTE,
								    destination_software_=softIdTS)
        runDataDescriptionIdGAIA = apolloDB.getRunDataDescriptionId(label_="newly_exposed.txt",
                                                                    source_software_=softIdFLUTE,
                                                                    destination_software_=softIdGAIA)
        SQLString = 'INSERT INTO run_data(run_Id,description_id,content_id) values ("%s","%d","%d")' % (simulationRunId,
                                                                                                        runDataDescriptionIdTS,
                                                                                                        runDataContentId)
        if opts.no_db is False:
            apolloDB.query(SQLString)
        
        SQLString = 'INSERT INTO run_data(run_Id,description_id,content_id) values ("%s","%d","%d")' % (simulationRunId,
                                                                                                        runDataDescriptionIdGAIA,
                                                                                                        runDataContentId)
        if opts.no_db is False:                                                                                          
            apolloDB.query(SQLString)
        apolloDB.close()
        statusUpdate("LOG_FILES_WRITTEN", "%s" % datetime.datetime.now().strftime("%a %b %d %H:%M:%S EDT %Y"))    
            # ## Insert data into database
            
            # with open('test.txt','wb') as f:
            #    f.write("%s"%(''.join(outStr)))          
                              
    except Exception as e:
        error_exit(str(e))
        
        
    
    
    
