import http from "../http-common";

class SoldierService {
  getAllSoldiers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/soldier/soldiers`, searchDTO);
  }

  get(soldierId) {
    return this.getRequest(`/soldier/${soldierId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/soldier?field=${matchData}`, null);
  }

  addSoldier(data) {
    return http.post("/soldier/addSoldier", data);
  }

  update(data) {
  	return http.post("/soldier/updateSoldier", data);
  }
  
  uploadImage(data,soldierId) {
  	return http.postForm("/soldier/uploadImage/"+soldierId, data);
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

export default new SoldierService();
