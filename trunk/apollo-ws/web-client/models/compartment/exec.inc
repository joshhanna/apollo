<?php
/*

    Copyright (C) 2011 University of Pittsburgh

    This file is part of Apollo.

    Apollo is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of
    the License, or (at your option) any later version.

    Apollo is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with Apollo.  If not, see <http://www.gnu.org/licenses/>.

*/

/**
 *
 * @author Yang Hu <yah14@pitt.edu>
 */
namespace models\compartment;

require_once 'apollo\type.inc';
require_once 'apollo\constant.inc';

require_once 'models\compartment\constant.inc';

function exec($params) {
	$classmap = array ('ModelID' => '\apollo\ModelID', 'RunResultRequestObjType' => '\apollo\RunResultRequestObjType' );

	$client = new \SoapClient ( \apollo\get_wsdl (), array ('trace' => true, 'classmap' => $classmap ) );

	$id = get_modelid ();
	$runObj = $client->getDefaultCompartmentModelRunRequestObj ( array ('arg0' => $id ) );

	//Fill in the correct parameters
	$runObj = $runObj->return;
	$runObj->ModelID = $id;
	$runObj->Parameters->Recognized->Username = "";
	$runObj->Parameters->Recognized->Password = "";

	//fillinRecognized
	$recognized = $runObj->Parameters->Recognized;
	$recognized->TemporalGranularity = $params['TemporalGranularity'];
	$recognized->TemporalIncrement= $params['TemporalIncrement'];
	$recognized->RunLength = $params['RunLength'];

	$recognized->InitialCompartmentSizes->named_Compartment = $params['InitialCompartmentSizes'];

	$recognized->BeginTreatmentInterval = $params['BeginTreatmentInterval'];
	$recognized->ProphylaxisEfficacy = $params['ProphylaxisEfficacy'];
	$recognized->ProdromalTreatmentEfficacy = $params['ProdromalTreatmentEfficacy'];
	$recognized->FulminantTreatmentEfficacy = $params['FulminantTreatmentEfficacy'];
//	$recognized->VaccinationSchedule->value = $params['VaccinationSchedule'];
//
//	//InitialCompartmentSizes
//	$recognized->InitialCompartmentSizes->Susceptible->Number = $params['Susceptible'];
//	$recognized->InitialCompartmentSizes->Exposed = $params['Exposed'];
//	$recognized->InitialCompartmentSizes->Infectious = $params['Infectious'];
//	$recognized->InitialCompartmentSizes->Recovered = $params['Recovered'];
//	//end InitialCompartmentSizes
//
//	//$temporalGranularity = $runObj->Parameters->Recognized->TemporalGranularity;
//
	$apolloResponse = $client->runCompartmentModelRequest ( array ('arg0' => $runObj ) );
	$apolloResponse = $apolloResponse->return;

	if ($apolloResponse->id == - 1) { //cache hit
		$apolloResponse = $apolloResponse->cachedResult;
	} else {
		$runId = $apolloResponse->id;

		while ( true ) {
			$status = $client->getStatus ( array ('arg0' => $runId ) );
			$status = $status->return;
			if (strcmp ( $status, 'COMPLETED' ) == 0) {
				$rrro = new \apollo\RunResultRequestObjType ( $runId, '', '' );
				$apolloResponse = $client->getCompartmentModelRunResults ( array ('arg0' => $rrro ) );
				$apolloResponse = $apolloResponse->return;
				break;
			} else
				sleep ( 1 ); //sleep for one second
		}
	}

	$curves ['Prodromal'] = $apolloResponse->results->Recognized->EpiCurve->Prodromal->value;
	$curves ['Fulminant'] = $apolloResponse->results->Recognized->EpiCurve->Fulminant->value;
	$curves ['Dead'] = $apolloResponse->results->Recognized->EpiCurve->Dead->value;
	$curves ['Prophylaxed'] = $apolloResponse->results->Recognized->EpiCurve->Prophylaxed->value;
//	$curves ['Incidence'] = $apolloResponse->results->Recognized->IncidenceCurve->value;

//	$curves = Array();
	return $curves;
}
?>