function initAutocomplete() {
    const map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 37.534522948, lng: 126.994243914  },
        zoom: 15,
        mapTypeId: "roadmap",
    });
    // Create the search box and link it to the UI element.
    const input = document.getElementById("pac-input"); //input 입력받는 곳
    const searchBox = new google.maps.places.SearchBox(input);
    const service = new google.maps.places.PlacesService(map);

    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
    map.addListener("bounds_changed", () => {
        searchBox.setBounds(map.getBounds());
    });

    let markers = [];


    var curDate = new Date();

    var curTime = curDate.getFullYear() + "-" +
        (curDate.getMonth() + 1) + "-" + curDate.getDate() + " " +
        curDate.getHours() + ":" + curDate.getMinutes() + ":" +
        curDate.getSeconds();


    let infowindow; // 현재 열려 있는 인포윈도우를 저장할 변수

    function myClickListener(place, marker) {
        const content = document.createElement("div");
        const nameElement = document.createElement("h2");
        const placeIdElement = document.createElement("p");
        const placeAddressElement = document.createElement("p");

        nameElement.textContent = place.name;
        content.appendChild(nameElement);

        let openStatus = "";
        if (place.opening_hours && place.opening_hours.open_now) {
            openStatus = "Open";
        } else {
            openStatus = "Closed";
        }
        placeIdElement.textContent = openStatus;
        content.appendChild(placeIdElement);

        placeAddressElement.textContent = place.formatted_address;
        content.appendChild(placeAddressElement);

        // 기존에 열려 있는 인포윈도우가 있으면 닫아줍니다.
        if (infowindow) {
            infowindow.close();
        }

        // 새로운 인포윈도우를 생성하고 엽니다.
        infowindow = new google.maps.InfoWindow({
            content: content,
            position: marker.getPosition(), // 클릭한 위치에 인포윈도우를 띄우기 위해 마커 위치를 사용합니다.
        });
        infowindow.open(map);
    }


    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener("places_changed", () => {
        const places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Clear out the old markers.
        markers.forEach((marker) => {
            marker.setMap(null);
        });
        markers = [];

        // For each place, get the icon, name and location.
        const bounds = new google.maps.LatLngBounds();

        places.forEach((place) => {
            if (!place.geometry || !place.geometry.location) {
                console.log("Returned place contains no geometry");
                return;
            }

            const icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25),
            };

            // Create a marker for each place.

            marker = new google.maps.Marker({
                map,
                icon,
                title: place.name,
                position: place.geometry.location,
            });

            markers.push(marker);


            google.maps.event.addListener(marker, "click", () => {
                myClickListener(place, marker);
            });

            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
}

window.initAutocomplete = initAutocomplete;

//key 값
src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg&callback=initAutocomplete&libraries=places&v=weekly"
defer