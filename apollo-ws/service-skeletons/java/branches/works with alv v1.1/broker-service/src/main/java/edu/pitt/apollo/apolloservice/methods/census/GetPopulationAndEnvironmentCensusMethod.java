package edu.pitt.apollo.apolloservice.methods.census;

import edu.pitt.apollo.types.v2_1_0.GetPopulationAndEnvironmentCensusResult;
import edu.pitt.apollo.types.v2_1_0.MethodCallStatus;
import edu.pitt.apollo.types.v2_1_0.MethodCallStatusEnum;
import edu.pitt.apollo.types.v2_1_0.SoftwareIdentification;

/**
 *
 * Author: Nick Millett
 * Email: nick.millett@gmail.com
 * Date: May 9, 2014
 * Time: 1:59:58 PM
 * Class: GetPopulationAndEnvironmentCensusMethod
 * IDE: NetBeans 6.9.1
 */
public class GetPopulationAndEnvironmentCensusMethod extends PopulationAndEnvironmentCensusMethod {

    public static GetPopulationAndEnvironmentCensusResult getPopulationAndEnvironmentCensus(SoftwareIdentification simulatorIdentification,
            String location) {
        GetPopulationAndEnvironmentCensusResult res = new GetPopulationAndEnvironmentCensusResult();
        MethodCallStatus status = new MethodCallStatus();
        status.setStatus(MethodCallStatusEnum.COMPLETED);
        status.setMessage("Success!");
        res.setPopulationAndEnvironmentCensus(getPopulationAndEnvironmentCensusGivenINCITS(location));
        return res;
    }
}
