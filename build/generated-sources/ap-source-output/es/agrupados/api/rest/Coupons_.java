package es.agrupados.api.rest;

import es.agrupados.api.rest.ApplicationUsers;
import es.agrupados.api.rest.Offers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-10T16:03:20")
@StaticMetamodel(Coupons.class)
public class Coupons_ { 

    public static volatile SingularAttribute<Coupons, Date> purchaseDatetime;
    public static volatile SingularAttribute<Coupons, Offers> offersId;
    public static volatile SingularAttribute<Coupons, String> generatedCode;
    public static volatile SingularAttribute<Coupons, Integer> id;
    public static volatile SingularAttribute<Coupons, Boolean> used;
    public static volatile SingularAttribute<Coupons, ApplicationUsers> applicationUsersId;

}