import http from "../http-common";

class CommunicationService {
  getAllCommunications(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/communication/communications`, searchDTO);
  }

  get(communicationId) {
    return this.getRequest(`/communication/${communicationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/communication?field=${matchData}`, null);
  }

  addCommunication(data) {
    return http.post("/communication/addCommunication", data);
  }

  update(data) {
  	return http.post("/communication/updateCommunication", data);
  }
  
  uploadImage(data,communicationId) {
  	return http.postForm("/communication/uploadImage/"+communicationId, data);
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

export default new CommunicationService();
