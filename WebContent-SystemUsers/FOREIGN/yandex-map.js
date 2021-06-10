"UTF-8"
/**
 * Функционалности за баратање са мапама, издање Јандекс. 
 */
function load_map_into(latitude, longitude, refresh, map_id, map_logo_id){
	document.getElementById(map_id).innerHTML='';
	ymaps.ready(function () { 
	    var myMap = new ymaps.Map(map_id, {
	            center: [latitude, longitude],
	            zoom: 9
	        }, {
	            searchControlProvider: 'yandex#search'
	        }),
	
	        MyIconContentLayout = ymaps.templateLayoutFactory.createClass(
	            '<div style="color: #FFFFFF; font-weight: bold;">$[properties.iconContent]</div>'
	        ),
	
	        myPlacemark = new ymaps.Placemark(myMap.getCenter(), {
	            hintContent: 'Собственный значок метки',
	            balloonContent: 'Это красивая метка'
	        }, {
	            iconLayout: 'default#image',
	            iconImageHref: 'INTERNET-RESOURCES/Yandex/images/myIcon.gif',
	            iconImageSize: [30, 42],
	            iconImageOffset: [-5, -38]
	        }),
	
	        myPlacemarkWithContent = new ymaps.Placemark([latitude, longitude], {
	            hintContent: 'Собственный значок метки с контентом',
	            balloonContent: 'А эта — новогодняя',
	            iconContent: '12'
	        }, {
	            iconLayout: 'default#imageWithContent',
	            iconImageHref: 'INTERNET-RESOURCES/Yandex/images/ball.png',
	            iconImageSize: [48, 48],
	            iconImageOffset: [-24, -24],
	            iconContentOffset: [15, 15],
	            iconContentLayout: MyIconContentLayout
	        });
	
	    myMap.geoObjects
	        .add(myPlacemark)
	        .add(myPlacemarkWithContent);
		refresh(map_id, map_logo_id);
	});
}

function load_map(latitude, longitude, refresh){
	load_map_into(latitude, longitude, refresh, 'crc_map', 'crc_map_logo');
}