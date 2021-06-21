function selectDepartures(){
    document.getElementById("departures-list").classList.remove("hidden");
    document.getElementById("arrivals-list").classList.add("hidden");
    document.getElementById(" tab-departure").classList.add("selected");
    document.getElementById(" tab-arrival").classList.remove("selected");

}

function selectArrivals(){
    document.getElementById("departures-list").classList.add("hidden");
    document.getElementById("arrivals-list").classList.remove("hidden");
    document.getElementById(" tab-departure").classList.remove("selected");
    document.getElementById(" tab-arrival").classList.add("selected");

}