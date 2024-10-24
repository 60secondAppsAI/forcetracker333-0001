import http from "../http-common";

class LocationService {
  getAllLocations(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/location/locations`, searchDTO);
  }

  get(locationId) {
    return this.getRequest(`/location/${locationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/location?field=${matchData}`, null);
  }

  addLocation(data) {
    return http.post("/location/addLocation", data);
  }

  update(data) {
  	return http.post("/location/updateLocation", data);
  }
  
  uploadImage(data,locationId) {
  	return http.postForm("/location/uploadImage/"+locationId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new LocationService();
