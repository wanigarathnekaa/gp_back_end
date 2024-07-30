package com.example.gp_back_end.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_ROLE_CREATOR("admin:role Creation"),
    ADMIN_USER_CREATOR("admin:user Creation"),
    ADMIN_UPDATE_USER("admin:update User"),
    ADMIN_DELETE_USER("admin:delete User"),
    ADMIN_FORM_CREATOR("admin:form Creation"),
    ADMIN_FORM_EDITOR("admin:form Editor"),
    ADMIN_FORM_DELETE("admin:form Delete"),
    ADMIN_FORM_PUBLISHER("admin:form Publisher"),
    ADMIN_SUBMISSION_VIEWER("admin:submission Viewer"),
    ADMIN_SUBMISSION_EDITOR("admin:submission Editor"),
    ADMIN_PEER_ADDER("admin:peer Adder"),

    STUDENT_FORM_SUBMITTER("student:form Submitter"),

    LECTURER_FORM_SUBMITTER("lecturer:form Submitter"),
    ;

    private final String permission;

}
