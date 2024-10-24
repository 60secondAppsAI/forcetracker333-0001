import http from "../http-common";

class FleetService {
  getAllFleets(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/fleet/fleets`, searchDTO);
  }

  get(fleetId) {
    return this.getRequest(`/fleet/${fleetId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/fleet?field=${matchData}`, null);
  }

  addFleet(data) {
    return http.post("/fleet/addFleet", data);
  }

  update(data) {
  	return http.post("/fleet/updateFleet", data);
  }
  
  uploadImage(data,fleetId) {
  	return http.postForm("/fleet/uploadImage/"+fleetId, data);
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

export default new FleetService();
