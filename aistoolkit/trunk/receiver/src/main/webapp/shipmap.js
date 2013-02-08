var map;
var layer_vector;
var features = [];
var selectCtrl;
var selectedFeature;

function initMap() {	
   map = new OpenLayers.Map('map');   
   var layer_mapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
   var layer_seamark = new OpenLayers.Layer.TMS("Seezeichen", "http://tiles.openseamap.org/seamark/", {numZoomLevels: 18, type: 'png', getURL: getTileURL, isBaseLayer: false, displayOutsideMaxExtent: true});
   var layer_harbour = new OpenLayers.Layer.Markers("Häfen", {projection: new OpenLayers.Projection("EPSG:4326"), visibility: true, displayOutsideMaxExtent:true});
   layer_vector = new OpenLayers.Layer.Vector("Simple Geometry", {styleMap: new OpenLayers.StyleMap({"default": {externalGraphic: "${graphic}", graphicHeight: 15, rotation: "${heading}"}})});   
   map.addLayers([layer_mapnik, layer_seamark, layer_harbour, layer_vector]);   
   map.zoomToMaxExtent();
}

function addMarker(longitude, latitude, heading, text) {	
	var lonLat = new OpenLayers.LonLat(longitude, latitude).transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());	
	var point = new OpenLayers.Geometry.Point(lonLat.lon, lonLat.lat);	
	var vector = new OpenLayers.Feature.Vector(point);	
	if(511 == heading) {
		vector.attributes = {
			graphic: 'icons/no-heading.png',
			heading: 0,
			text: text
		};
	} else {
		vector.attributes = {
			graphic: 'icons/heading.png',
			heading: heading,
			text: text
		};
	}	
	features.push(vector);
	layer_vector.addFeatures(features);
	
    selectCtrl = new OpenLayers.Control.SelectFeature(layer_vector, {onSelect: onFeatureSelect, onUnselect: onFeatureUnselect});
    map.addControl(selectCtrl);
    selectCtrl.activate();
}

function onFeatureSelect(feature) {
    selectedFeature = feature;
    popup = new OpenLayers.Popup.FramedCloud(null, feature.geometry.getBounds().getCenterLonLat(), null, '<p style="font-family:Calibri;font-size:11px;">' + feature.attributes.text + '</p>', null, false, onPopupClose);
    feature.popup = popup;
    map.addPopup(popup);
}

function onFeatureUnselect(feature) {
    map.removePopup(feature.popup);
    feature.popup.destroy();
    feature.popup = null;
}

function onPopupClose(evt) {
    selectCtrl.unselect(selectedFeature);
}