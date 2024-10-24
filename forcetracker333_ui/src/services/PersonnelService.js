import http from "../http-common";

class PersonnelService {
  getAllPersonnels(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/personnel/personnels`, searchDTO);
  }

  get(personnelId) {
    return this.getRequest(`/personnel/${personnelId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/personnel?field=${matchData}`, null);
  }

  addPersonnel(data) {
    return http.post("/personnel/addPersonnel", data);
  }

  update(data) {
  	return http.post("/personnel/updatePersonnel", data);
  }
  
  uploadImage(data,personnelId) {
  	return http.postForm("/personnel/uploadImage/"+personnelId, data);
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

export default new PersonnelService();
