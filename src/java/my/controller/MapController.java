/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import com.gisfaces.event.MapClickEvent;
import com.gisfaces.event.MapExtentEvent;
import com.gisfaces.event.MapGeoLocationEvent;
import com.gisfaces.event.MapGraphicDragEvent;
import com.gisfaces.event.MapSelectEvent;
import com.gisfaces.model.GraphicsModel;
import com.gisfaces.model.Marker;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
public class MapController {

    private String background;
    private double latitude;
    private double longitude;
    private int zoom;
    private double opacity;
    private boolean visible;
    private String where;
    private String json;
    private GraphicsModel cabGraphicsModel;

    public MapController() {
        super();
        reset();
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
        return cabGraphicsModel;
    }

    public void setCabGraphicsModel(GraphicsModel cabGraphicsModel) {
        this.cabGraphicsModel = cabGraphicsModel;
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
        this.background = "osm";
        this.latitude = 30.304353;
        this.longitude = -81.655535;
        this.zoom = 10;
        this.opacity = 1.0;
        this.visible = true;
        this.where = "Magnitude >= 2";
        
        buildCabGraphicsModel();
    }

    private void buildCabGraphicsModel() {
        this.cabGraphicsModel = new GraphicsModel();
        this.cabGraphicsModel.setName("Cab Finder");

        List<Marker> markers = this.cabGraphicsModel.getMarkers();
        markers.add(buildCabMarker(30.304353, -81.655535, "1980 San Marco Blvd, Jacksonville, FL 32207"));
        markers.add(buildCabMarker(30.312096, -81.680833, "1650 Margaret St, Jacksonville, FL 32204"));
        markers.add(buildCabMarker(30.2432613, -81.5986099, "7153 Philips Hwy, Jacksonville, FL 32256"));
        markers.add(buildCabMarker(30.278487, -81.720025, "4495 Roosevelt Blvd, Jacksonville, FL 32210"));
        markers.add(buildCabMarker(30.292561, -81.601978, "5960 Beach Blvd, Jacksonville, FL 32216"));
        markers.add(buildCabMarker(30.220144, -81.551926, "8221 Southside Blvd, Jacksonville, FL 32256"));
        markers.add(buildCabMarker(30.204192, -81.617150, "9661 San Jose Blvd, Jacksonville, FL 32257"));
        markers.add(buildCabMarker(30.260889, -81.645445, "1500 University Blvd W, Jacksonville, FL 32217"));
        markers.add(buildCabMarker(30.429896, -81.662830, "1044 Dunn Ave, Jacksonville, FL 32218"));
        markers.add(buildCabMarker(30.316828, -81.558313, "9301 Atlantic Blvd #101, Jacksonville, FL 32225"));
        markers.add(buildCabMarker(30.25813, -81.5262888, "10281 Midtown Pkwy #203, Jacksonville, FL 32246"));
        markers.add(buildCabMarker(30.190558, -81.551744, "9940 Southside Blvd, Jacksonville, FL 32256"));
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
        marker.setImage("resources/img/taxi.png");
        marker.setHeight(25);
        marker.setWidth(25);
        
        return marker;
    }
}
