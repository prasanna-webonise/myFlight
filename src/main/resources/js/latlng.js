var polygon_points = new Array();
var map;
var marker;
var polygon_options;
var polygon;
var centerCoordinates;

window.onload=initialize;

function initialize(){
	L.mapbox.accessToken = 'pk.eyJ1IjoicHJhc2FuMjg5MyIsImEiOiJjaWZmN3AxbmE3emt0c2trbnZqcGN4bTl6In0.LJ220L_6CnwOfS5sAStp-g';
	map = L.mapbox.map('map', 'mapbox.streets')
    	.setView([18.5203,73.8567], 4);
	marker = new L.Marker(new L.LatLng(18.5203,73.8567));
	map.addLayer(marker);
	polygon_options = {
    	color: '#000'
	};
	map.on('click', function(e) {
		console.log(e.latlng);
		if(polygon!=null)
		map.removeLayer(polygon);
		polygon_points.push([e.latlng.lat,e.latlng.lng]);
		polygon = L.polygon(polygon_points, polygon_options).addTo(map);
		findCenter();
	});
}

function findCenter(){
	var bounds = new L.latLngBounds(polygon_points);
	centerCoordinates.setLattitude(bounds.getCenter().lat);
	centerCoordinates.setLongitude(bounds.getCenter().lng);
	console.log(bounds.getCenter().lat);
}