package edu.pitt.apollo.libraryservice.methods;

import edu.pitt.apollo.db.LibraryDbUtils;
import edu.pitt.apollo.db.LibraryUserRoleTypeEnum;
import edu.pitt.apollo.db.exceptions.ApolloDatabaseException;
import edu.pitt.apollo.library_service_types.v2_1_0.GetCommentsMessage;
import edu.pitt.apollo.library_service_types.v2_1_0.GetCommentsResult;
import edu.pitt.apollo.services_common.v2_1_0.Authentication;
import edu.pitt.apollo.services_common.v2_1_0.MethodCallStatus;
import edu.pitt.apollo.services_common.v2_1_0.MethodCallStatusEnum;

/**
 *
 * Author: Nick Millett
 * Email: nick.millett@gmail.com
 * Date: Nov 7, 2014
 * Time: 11:27:41 AM
 * Class: GetCommentsMethod
 */
public class GetCommentsMethod {

	public static GetCommentsResult getComments(LibraryDbUtils dbUtils, GetCommentsMessage message) {
		Authentication authentication = message.getAuthentication();
		String uri = message.getUri();
		int version = message.getVersion();
		
		GetCommentsResult result = new GetCommentsResult();
		MethodCallStatus status = new MethodCallStatus();
		
		try {
			boolean userAuthorized = dbUtils.authorizeUser(authentication, LibraryUserRoleTypeEnum.READONLY);
			if (userAuthorized) {
				result = dbUtils.getComments(uri, version);
				status.setStatus(MethodCallStatusEnum.COMPLETED);
			} else {
				status.setStatus(MethodCallStatusEnum.AUTHENTICATION_FAILURE);
				status.setMessage("You are not authorized to add reviewer comments.");
			}

		} catch (ApolloDatabaseException ex) {
			status.setStatus(MethodCallStatusEnum.FAILED);
			status.setMessage(ex.getMessage());
		}
		result.setStatus(status);

		return result;
	}
}
