package es.agrupados.api.rest;

import es.agrupados.api.rest.ApplicationRoles;
import es.agrupados.api.rest.ApplicationUserDetails;
import es.agrupados.api.rest.Coupons;
import es.agrupados.api.rest.Offers;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-10T16:03:20")
@StaticMetamodel(ApplicationUsers.class)
public class ApplicationUsers_ { 

    public static volatile SingularAttribute<ApplicationUsers, String> password;
    public static volatile CollectionAttribute<ApplicationUsers, Coupons> couponsCollection;
    public static volatile SingularAttribute<ApplicationUsers, ApplicationRoles> role;
    public static volatile SingularAttribute<ApplicationUsers, Boolean> active;
    public static volatile SingularAttribute<ApplicationUsers, Integer> id;
    public static volatile SingularAttribute<ApplicationUsers, String> email;
    public static volatile CollectionAttribute<ApplicationUsers, ApplicationUserDetails> applicationUserDetailsCollection;
    public static volatile SingularAttribute<ApplicationUsers, String> username;
    public static volatile CollectionAttribute<ApplicationUsers, Offers> offersCollection;

}