document.addEventListener("DOMContentLoaded",function(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(gotlocation,failedToGetlocation)
    }
    else{
        document.getElementById("para-id").innerHTML = "Geolocation is not supported by this browser."
    }
})

function gotlocation(position){
    let latitude = position.coords.latitude
    let longitude = position.coords.longitude

    let url = `https://api.opencagedata.com/geocode/v1/json?q=${latitude}+${longitude}&key=d5de115436e04b7796331dbf9b8f3843`
    fetch(url)
        .then(response => response.json())
        .then(data =>{
            let district = data.results[0].components.state_district
            console.log("The city detected is ",district)
            sendCitytobackend(district)
        })
        .catch(error =>console.error("error fetching the location",error))

    
}
function failedToGetlocation(){
    console.log("Please give access to show you resources in your region")
}

function sendCitytobackend(district){
    fetch(`http://localhost:9898/alldonations?distrcit=${district}`)
        .then(response => response.json())
        .then(data => {
           const tablebody = document.getElementById("donation-table")
           tablebody.innerHTML="";
           data.forEach(donation =>{
                const row = document.createElement("tr")
                row.innerHTML=`
                <td>${donation.id}</td>
                <td>${donation.item_name}</td>
                <td>${donation.category}</td>
                <td>${donation.city}</td>
                <td>${donation.yearold}</td>
                <td>${donation.status}</td>
                <td>
                    <button onclick="bookingitem(${donation.id})">BookNow</button>
                </td>
                `
                tablebody.appendChild(row)
           })
        })
        .catch(error => console.error("error fetching donations ",error))
}
function bookingitem(id){
    fetch("/api/current-user")
        .then(response => response.text())
        .then(username => {
            if(username){
                sessionStorage.setItem("username",username);
                console.log("username stored:",username)

                fetch(`http://localhost:9898/book_donation_item?id=${id}&gettername=${encodeURIComponent(username)}`,{
                    method: "PUT",
                    headers:{
                        "Content-Type":"application/x-www-form-urlencoded",
                    },
                })
                .then(response => {
                    if(response.ok){
                        console.log(`Donation item ${id} booked by ${username}`);
                    }
                    else{
                        console.error("Failed to book donation item.");
                    }
                })
                .catch(error => console.error("Error booking donation:", error));
            }
            else{
                console.error("user is not logged in");
            }
        })
        .catch(error => console.error("Error fetching user:", error));
}




