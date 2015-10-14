var polygonPoints = [];
var map;
var marker;
var polygonOptions;
var polygon;
var centerCoordinates;

window.onload=initialize;

function initialize(){
	L.mapbox.accessToken = 'pk.eyJ1IjoicHJhc2FuMjg5MyIsImEiOiJjaWZmN3AxbmE3emt0c2trbnZqcGN4bTl6In0.LJ220L_6CnwOfS5sAStp-g';
	map = L.mapbox.map('map', 'mapbox.streets')
    	.setView([18.5203,73.8567], 4);
	marker = new L.Marker(new L.LatLng(18.5203,73.8567));
	map.addLayer(marker);
	polygonOptions = {
    	color: '#000'
	};
	map.on('click', function(e) {
		console.log(e.latlng);
		if(polygon)
		map.removeLayer(polygon);
		polygonPoints.push([e.latlng.lat,e.latlng.lng]);
		polygon = L.polygon(polygonPoints, polygonOptions).addTo(map);
		findCenter();
	});
}

function findCenter(){
	var bounds = new L.latLngBounds(polygonPoints);
	centerCoordinates.setLatitude(bounds.getCenter().lat);
	centerCoordinates.setLongitude(bounds.getCenter().lng);
	console.log(bounds.getCenter().lat);
}

function removeLayer(){
	map.removeLayer(polygon);
	polygonPoints=[];
}
