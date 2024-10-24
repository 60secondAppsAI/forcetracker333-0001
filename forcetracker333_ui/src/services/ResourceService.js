import http from "../http-common";

class ResourceService {
  getAllResources(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/resource/resources`, searchDTO);
  }

  get(resourceId) {
    return this.getRequest(`/resource/${resourceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/resource?field=${matchData}`, null);
  }

  addResource(data) {
    return http.post("/resource/addResource", data);
  }

  update(data) {
  	return http.post("/resource/updateResource", data);
  }
  
  uploadImage(data,resourceId) {
  	return http.postForm("/resource/uploadImage/"+resourceId, data);
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

export default new ResourceService();
