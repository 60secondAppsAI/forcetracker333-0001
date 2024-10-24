import http from "../http-common";

class OperationService {
  getAllOperations(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/operation/operations`, searchDTO);
  }

  get(operationId) {
    return this.getRequest(`/operation/${operationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/operation?field=${matchData}`, null);
  }

  addOperation(data) {
    return http.post("/operation/addOperation", data);
  }

  update(data) {
  	return http.post("/operation/updateOperation", data);
  }
  
  uploadImage(data,operationId) {
  	return http.postForm("/operation/uploadImage/"+operationId, data);
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

export default new OperationService();
