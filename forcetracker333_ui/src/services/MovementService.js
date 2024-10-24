import http from "../http-common";

class MovementService {
  getAllMovements(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/movement/movements`, searchDTO);
  }

  get(movementId) {
    return this.getRequest(`/movement/${movementId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/movement?field=${matchData}`, null);
  }

  addMovement(data) {
    return http.post("/movement/addMovement", data);
  }

  update(data) {
  	return http.post("/movement/updateMovement", data);
  }
  
  uploadImage(data,movementId) {
  	return http.postForm("/movement/uploadImage/"+movementId, data);
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

export default new MovementService();
