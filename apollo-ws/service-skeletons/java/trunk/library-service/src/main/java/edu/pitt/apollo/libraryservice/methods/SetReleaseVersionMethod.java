package edu.pitt.apollo.libraryservice.methods;

import edu.pitt.apollo.db.LibraryDbUtils;
import edu.pitt.apollo.db.LibraryUserRoleTypeEnum;
import edu.pitt.apollo.db.exceptions.ApolloDatabaseException;
import edu.pitt.apollo.library_service_types.v3_0_0.SetReleaseVersionMessage;
import edu.pitt.apollo.library_service_types.v3_0_0.SetReleaseVersionResult;
import edu.pitt.apollo.services_common.v3_0_0.Authentication;
import edu.pitt.apollo.services_common.v3_0_0.MethodCallStatus;
import edu.pitt.apollo.services_common.v3_0_0.MethodCallStatusEnum;

/**
 *
 * Author: Nick Millett
 Email: nick.millett@gmail.com
 Date: Nov 7, 2014
 Time: 10:49:57 AM
 Class: SetReleaseVersionMethod
 */
public class SetReleaseVersionMethod {

	public static SetReleaseVersionResult setReleaseVersion(LibraryDbUtils dbUtils, SetReleaseVersionMessage message) {

		Authentication authentication = message.getAuthentication();
		int urn = message.getUrn();
		String comment = message.getComment();
		int version = message.getVersion();

		SetReleaseVersionResult result = new SetReleaseVersionResult();
		MethodCallStatus status = new MethodCallStatus();
		result.setStatus(status);

		try {
			boolean userAuthorized = dbUtils.authorizeUser(authentication, LibraryUserRoleTypeEnum.COMMITTER);
			if (userAuthorized) {
				dbUtils.setReleaseVersion(urn, version, authentication, comment);
				status.setStatus(MethodCallStatusEnum.COMPLETED);
			} else {
				status.setStatus(MethodCallStatusEnum.AUTHENTICATION_FAILURE);
				status.setMessage("You are not authorized to set release versions.");
			}

		} catch (ApolloDatabaseException ex) {
			status.setStatus(MethodCallStatusEnum.FAILED);
			status.setMessage(ex.getMessage());
		}

		return result;
	}

}
