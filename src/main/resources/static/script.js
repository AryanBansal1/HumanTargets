function gotlocation(position){
    let latitude = position.coords.latitude
    let longitude = position.coords.longitude
}
function failedLocation(){
    console.log("failed to take the location")
}
if(navigator.geolocation){
    navigator.geolocation.getCurrentPosition(gotlocation,failedLocation)
}
else{
    document.getElementById("").innerHTML="Geolocation is not supported by this browser."
}
