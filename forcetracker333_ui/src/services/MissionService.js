import http from "../http-common";

class MissionService {
  getAllMissions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/mission/missions`, searchDTO);
  }

  get(missionId) {
    return this.getRequest(`/mission/${missionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/mission?field=${matchData}`, null);
  }

  addMission(data) {
    return http.post("/mission/addMission", data);
  }

  update(data) {
  	return http.post("/mission/updateMission", data);
  }
  
  uploadImage(data,missionId) {
  	return http.postForm("/mission/uploadImage/"+missionId, data);
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

export default new MissionService();
