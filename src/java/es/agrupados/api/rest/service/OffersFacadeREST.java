/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.agrupados.api.rest.service;

import es.agrupados.api.rest.ApplicationUserDetails;
import es.agrupados.api.rest.ApplicationUsers;
import es.agrupados.api.rest.Offers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mundakamacbook
 */
@Stateless
@Path("offers")
public class OffersFacadeREST extends AbstractFacade<Offers> {

    @PersistenceContext(unitName = "OffersRESTPU")
    private EntityManager em;
    
    @EJB
    private ApplicationUserDetailsFacadeREST usersDetailsFacade;
    //private ApplicationUsersFacadeREST usersFacade;

    public OffersFacadeREST() {
        super(Offers.class);
    }

//    @POST
//    @Override
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Offers entity) {
//        super.create(entity);
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Integer id, Offers entity) {
//        super.edit(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Integer id) {
//        super.remove(super.find(id));
//    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Offers find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Offers> findAllActiveOffers() {
        List<Offers> activeOffers = super.findAll()
                .stream()
                .filter(o -> o.getActive())
                .collect(Collectors.toList());
        return activeOffers;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Offers> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("description/{keyWord}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Offers> findByDescription(@PathParam("keyWord") String keyWord){
        TypedQuery<Offers> query = getEntityManager().createNamedQuery("Offers.findByDescriptionLike", Offers.class);
        query.setParameter("description", "%" + keyWord + "%");
        List<Offers> resultList = query.getResultList();
        List<Offers> activeOffers = resultList.stream().filter(offer -> offer.getActive()).collect(Collectors.toList());
        return activeOffers;
        
    }
    @GET
    @Path("price")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Offers> findByPrice(@QueryParam("minPrice") float minPrice, @QueryParam("maxPrice") float maxPrice){
        List<Offers> allOffers = super.findAll();
        List<Offers> offersByPrice = allOffers.stream()
                .filter(offer -> (offer.getOfferPrice() >= minPrice && offer.getOfferPrice() <= maxPrice) 
                        && (offer.getActive()))
                .collect(Collectors.toList());
        
        return offersByPrice;
    }
    
    @GET
    @Path("location")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Offers> findOffersByLocation(@QueryParam("lat") 
            double latitude, @QueryParam("lng") double longitude) {

        List<ApplicationUserDetails> usersDetailsList = usersDetailsFacade.findAll();
        
        List<ApplicationUserDetails> usersLocation = usersDetailsList
                .stream()
                .filter((user) -> (user.getLatitude() != null && user.getLongitude() != null))
                .filter((user) -> (((int) latitude == (int) user.getLatitude().doubleValue())
                        && ((int) longitude == (int) user.getLongitude().doubleValue())))
                .collect(Collectors.toList());
        
        List<ApplicationUsers> business = usersLocation
                .stream()
                .map(user -> user.getApplicationUsersId())
                .collect(Collectors.toList());
        
        List<Offers> offers = business.stream()
                .map(b -> b.getOffersCollection()
                        .stream().collect(Collectors.toList()))
                .collect(Collectors.toList())
                .stream().flatMap(List::stream)
                .filter(o -> o.getActive())
                .collect(Collectors.toList());
        
        return offers;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Offers> findByTitle(@QueryParam("title") String title){
        TypedQuery<Offers> query = getEntityManager().createNamedQuery("Offers.findByTitle", Offers.class);
        query.setParameter("title", "%"+title+"%");
        List<Offers> offers = query.getResultList().stream().filter(o -> o.getActive()).collect(Collectors.toList());
        return offers;
    }
    
    @GET
    @Path("date")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Offers> findByDate(@QueryParam("start") String startDate, 
            @QueryParam("end") String endDate) throws ParseException{
        
        SimpleDateFormat startDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat endDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        startDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        endDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startDate1 = startDateFormat.parse(startDate);
        Date endDate1 = endDateFormat.parse(endDate);

        List<Offers> offers = super.findAll()
                .stream()
                .filter(o -> o.getStartDate().after(startDate1) 
                        && o.getEndDate().before(endDate1))
                .collect(Collectors.toList());
        return offers;
    }    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
