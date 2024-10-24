import http from "../http-common";

class SurveillanceService {
  getAllSurveillances(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/surveillance/surveillances`, searchDTO);
  }

  get(surveillanceId) {
    return this.getRequest(`/surveillance/${surveillanceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/surveillance?field=${matchData}`, null);
  }

  addSurveillance(data) {
    return http.post("/surveillance/addSurveillance", data);
  }

  update(data) {
  	return http.post("/surveillance/updateSurveillance", data);
  }
  
  uploadImage(data,surveillanceId) {
  	return http.postForm("/surveillance/uploadImage/"+surveillanceId, data);
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

export default new SurveillanceService();
