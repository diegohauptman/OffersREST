package es.agrupados.api.rest;

import es.agrupados.api.rest.ApplicationUsers;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-10T16:03:20")
@StaticMetamodel(ApplicationRoles.class)
public class ApplicationRoles_ { 

    public static volatile CollectionAttribute<ApplicationRoles, ApplicationUsers> applicationUsersCollection;
    public static volatile SingularAttribute<ApplicationRoles, String> rolename;
    public static volatile SingularAttribute<ApplicationRoles, Integer> id;

}