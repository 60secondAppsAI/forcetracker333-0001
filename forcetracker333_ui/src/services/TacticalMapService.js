import http from "../http-common";

class TacticalMapService {
  getAllTacticalMaps(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/tacticalMap/tacticalMaps`, searchDTO);
  }

  get(tacticalMapId) {
    return this.getRequest(`/tacticalMap/${tacticalMapId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/tacticalMap?field=${matchData}`, null);
  }

  addTacticalMap(data) {
    return http.post("/tacticalMap/addTacticalMap", data);
  }

  update(data) {
  	return http.post("/tacticalMap/updateTacticalMap", data);
  }
  
  uploadImage(data,tacticalMapId) {
  	return http.postForm("/tacticalMap/uploadImage/"+tacticalMapId, data);
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

export default new TacticalMapService();
