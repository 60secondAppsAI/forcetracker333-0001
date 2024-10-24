import http from "../http-common";

class CommandCenterService {
  getAllCommandCenters(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/commandCenter/commandCenters`, searchDTO);
  }

  get(commandCenterId) {
    return this.getRequest(`/commandCenter/${commandCenterId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/commandCenter?field=${matchData}`, null);
  }

  addCommandCenter(data) {
    return http.post("/commandCenter/addCommandCenter", data);
  }

  update(data) {
  	return http.post("/commandCenter/updateCommandCenter", data);
  }
  
  uploadImage(data,commandCenterId) {
  	return http.postForm("/commandCenter/uploadImage/"+commandCenterId, data);
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

export default new CommandCenterService();
