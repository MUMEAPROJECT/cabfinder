/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import boundary.DriverFacadeLocal;
import com.gisfaces.event.MapClickEvent;
import com.gisfaces.event.MapExtentEvent;
import com.gisfaces.event.MapGeoLocationEvent;
import com.gisfaces.event.MapGraphicDragEvent;
import com.gisfaces.event.MapSelectEvent;
import com.gisfaces.model.GraphicsModel;
import com.gisfaces.model.Marker;
import entities.Driver;
import entities.Location;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author Santosh
 */
@ManagedBean
@SessionScoped
public class MapController implements Serializable {

    private String background;
    private double latitude;
    private double longitude;
    private int zoom;
    private double opacity;
    private boolean visible;
    private String where;
    private String json;
    private GraphicsModel cabGraphicsModel;
    private GraphicsModel driverGraphicsModel;
    private boolean driverStatus;
    private GraphicsModel userGraphicsModel;
    private boolean userStatus;
    private String map;

    @EJB
    DriverFacadeLocal dfacade;

    public DriverFacadeLocal getDfacade() {
        return dfacade;
    }

    public void setDfacade(DriverFacadeLocal dfacade) {
        this.dfacade = dfacade;
    }

  //  private DriverController driverController;
    public MapController() {
        super();
        reset();
        driverStatus = true;
        userStatus = true;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public boolean isDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(boolean driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public GraphicsModel getCabGraphicsModel() {
        buildCabGraphicsModel();
        return cabGraphicsModel;
    }

    public void setCabGraphicsModel(GraphicsModel cabGraphicsModel) {
        this.cabGraphicsModel = cabGraphicsModel;
    }

    public GraphicsModel getDriverGraphicsModel(Driver driver) {
        buildDriverGraphicsModel(driver);
        if (driverStatus == true) {
            return driverGraphicsModel;
        } else {
            return null;
        }
    }

    public void setDriverGraphicsModel(GraphicsModel driverGraphicsModel) {
        this.driverGraphicsModel = driverGraphicsModel;
    }

    public GraphicsModel getUserGraphicsModel() {
        if (userStatus == true) {
            return userGraphicsModel;
        } else {
            return null;
        }
    }

    public void setUserGraphicsModel(GraphicsModel userGraphicsModel) {
        this.userGraphicsModel = userGraphicsModel;
    }

    public void doMapClickListener(AjaxBehaviorEvent event) {
        MapClickEvent e = (MapClickEvent) event;

        String summary = "Map Click Event";
        String detail = String.format("Latitude='%s', Longitude='%s'", e.getLatitude(), e.getLongitude());

        System.out.println(String.format("%s: %s", summary, detail));
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void doMapExtentListener(AjaxBehaviorEvent event) {
        MapExtentEvent e = (MapExtentEvent) event;

        this.latitude = e.getLatitude();
        this.longitude = e.getLongitude();
        this.zoom = e.getZoom();

        String summary = "Map Extent Update Event";
        String detail = String.format("Latitude='%s', Longitude='%s', Zoom='%s'", e.getLatitude(), e.getLongitude(), e.getZoom());

        System.out.println(String.format("%s: %s", summary, detail));
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void doMapSelectListener(AjaxBehaviorEvent event) {
        MapSelectEvent e = (MapSelectEvent) event;

        this.json = e.getAttributesJson();

        String summary = "Map Select Event";
        String detail = String.format("Service ID='%s', Layer ID='%s', Attributes='%s'", e.getServiceId(), e.getLayerId(), e.getAttributesJson());

        System.out.println(String.format("%s: %s", summary, detail));
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void doMapGraphicDragListener(AjaxBehaviorEvent event) {
        MapGraphicDragEvent e = (MapGraphicDragEvent) event;

        this.json = e.getAttributesJson();

        String summary = "Map Graphic Drag Event";
        String detail = String.format("Latitude='%s', Longitude='%s', Attributes='%s'", e.getLatitude(), e.getLongitude(), e.getAttributesJson());

        System.out.println(String.format("%s: %s", summary, detail));
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void doMapGeoLocationListener(AjaxBehaviorEvent event) {
        MapGeoLocationEvent e = (MapGeoLocationEvent) event;

        this.latitude = e.getLatitude();
        this.longitude = e.getLongitude();

        String summary = "Map Geo-Location Event";
        String detail = String.format("Latitude='%s', Longitude='%s', Heading='%s', Speed='%s', Altitude='%s', Timestamp='%s'", e.getLatitude(), e.getLongitude(), e.getHeading(), e.getSpeed(), e.getAltitude(), e.getTimestamp());

        System.out.println(String.format("%s: %s", summary, detail));
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void reset() {
        this.background = "topo";
//        this.latitude = 30.304353;
//        this.longitude = -81.655535;
        this.latitude = 41.0226793;
        this.longitude = -91.967119;
        this.zoom = 13;
        this.opacity = 1.0;
        this.visible = true;
        this.where = "Magnitude >= 2";

        buildUserGraphicsModel();
    }

    @TransactionAttribute
    private void buildCabGraphicsModel() {
        this.cabGraphicsModel = new GraphicsModel();
        this.cabGraphicsModel.setName("Cab Finder > Driver");

        List<Marker> markers = this.cabGraphicsModel.getMarkers();
        if (dfacade == null) {
            System.out.println("null dfacade");
        }
        List<Driver> drivers = dfacade.findAll();

        for (Driver driver : drivers) {
            for (Location location : driver.getLocation()) {
                markers.add(buildCabMarker(location.getLat(), location.getLon(), location.getStreet()));
            }
        }
//        markers.add(buildCabMarker(30.304353, -81.655535, "1980 San Marco Blvd, Jacksonville, FL 32207"));
//        markers.add(buildCabMarker(30.312096, -81.680833, "1650 Margaret St, Jacksonville, FL 32204"));
//        markers.add(buildCabMarker(30.2432613, -81.5986099, "7153 Philips Hwy, Jacksonville, FL 32256"));
//        markers.add(buildCabMarker(30.278487, -81.720025, "4495 Roosevelt Blvd, Jacksonville, FL 32210"));
//        markers.add(buildCabMarker(30.292561, -81.601978, "5960 Beach Blvd, Jacksonville, FL 32216"));
//        markers.add(buildCabMarker(30.220144, -81.551926, "8221 Southside Blvd, Jacksonville, FL 32256"));
//        markers.add(buildCabMarker(30.204192, -81.617150, "9661 San Jose Blvd, Jacksonville, FL 32257"));
//        markers.add(buildCabMarker(30.260889, -81.645445, "1500 University Blvd W, Jacksonville, FL 32217"));
//        markers.add(buildCabMarker(30.429896, -81.662830, "1044 Dunn Ave, Jacksonville, FL 32218"));
//        markers.add(buildCabMarker(30.316828, -81.558313, "9301 Atlantic Blvd #101, Jacksonville, FL 32225"));
//        markers.add(buildCabMarker(30.25813, -81.5262888, "10281 Midtown Pkwy #203, Jacksonville, FL 32246"));
//        markers.add(buildCabMarker(30.190558, -81.551744, "9940 Southside Blvd, Jacksonville, FL 32256"));
    }

    private Marker buildCabMarker(double latitude, double longitude, String address) {
        Map<String, Object> attributes = new LinkedHashMap<String, Object>();
        attributes.put("Address", address);
        attributes.put("Driver Name", "Harke");
        attributes.put("Location", "Location");
        attributes.put("Phone No.", "987654321");

        Marker marker = new Marker();
        marker.setLatitude(latitude);
        marker.setLongitude(longitude);
        marker.setAttributes(attributes);
        //marker.setDraggable(true);
        marker.setImage("../resources/img/taxi_placemark.png");
        marker.setHeight(30);
        marker.setWidth(25);

        return marker;
    }

    public void buildDriverGraphicsModel(Driver driver) {
        this.driverGraphicsModel = new GraphicsModel();
        this.driverGraphicsModel.setName("Cab Finder > YOU");

        List<Marker> markers = this.driverGraphicsModel.getMarkers();
        List<Location> locationList = driver.getLocation();

        double lat = 0, lon = 0, counter = 0;
        for (Location l : locationList) {
            markers.add(buildDriverMarker(l.getLat(), l.getLon(), l.getStreet()));
            counter++;
            lat += l.getLat();
            lon += l.getLon();
        }
        this.latitude = lat / counter;
        this.longitude = lon / counter;
    }

    public List<Location> getDriverList() {
        return null;

    }

    public Marker buildDriverMarker(double lat, double lon, String street) {
        Map<String, Object> attributes = new LinkedHashMap<String, Object>();
        attributes.put("Street", street);

        Marker marker = new Marker();
        marker.setLatitude(lat);
        marker.setLongitude(lon);
        marker.setAttributes(attributes);
        //marker.setDraggable(true);
        marker.setImage("../resources/img/dmarker.png");
        marker.setHeight(30);
        marker.setWidth(25);

        return marker;
    }

    public void buildUserGraphicsModel() {
        this.userGraphicsModel = new GraphicsModel();
        this.userGraphicsModel.setName("Cab Finder > User");

        List<Marker> markers = this.userGraphicsModel.getMarkers();
        markers.add(buildUserMarker(41.00656363088295, -91.95743432800263, "1980 San Marco Blvd, Jacksonville, FL 32207"));
        markers.add(buildUserMarker(41.007535185596815, -91.96245542327866, "1650 Margaret St, Jacksonville, FL 32204"));
        markers.add(buildUserMarker(41.00662840164286, -91.96764817993162, "7153 Philips Hwy, Jacksonville, FL 32256"));
        markers.add(buildUserMarker(41.01284609816806, -91.96507325927725, "7153 Philips Hwy, Jacksonville, FL 32256"));
    }

    private Marker buildUserMarker(double latitude, double longitude, String address) {
        Map<String, Object> attributes = new LinkedHashMap<String, Object>();
        attributes.put("Address", address);
        attributes.put("Driver Name", "Harke");
        attributes.put("Location", "Location");
        attributes.put("Phone No.", "987654321");

        Marker marker = new Marker();
        marker.setLatitude(latitude);
        marker.setLongitude(longitude);
        marker.setAttributes(attributes);
        //marker.setDraggable(true);
        marker.setImage("../resources/img/userp.png");
        marker.setHeight(20);
        marker.setWidth(16);

        return marker;
    }
}
