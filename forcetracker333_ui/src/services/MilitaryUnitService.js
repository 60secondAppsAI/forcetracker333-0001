import http from "../http-common";

class MilitaryUnitService {
  getAllMilitaryUnits(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/militaryUnit/militaryUnits`, searchDTO);
  }

  get(militaryUnitId) {
    return this.getRequest(`/militaryUnit/${militaryUnitId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/militaryUnit?field=${matchData}`, null);
  }

  addMilitaryUnit(data) {
    return http.post("/militaryUnit/addMilitaryUnit", data);
  }

  update(data) {
  	return http.post("/militaryUnit/updateMilitaryUnit", data);
  }
  
  uploadImage(data,militaryUnitId) {
  	return http.postForm("/militaryUnit/uploadImage/"+militaryUnitId, data);
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

export default new MilitaryUnitService();
