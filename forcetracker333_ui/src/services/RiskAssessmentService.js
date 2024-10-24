import http from "../http-common";

class RiskAssessmentService {
  getAllRiskAssessments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/riskAssessment/riskAssessments`, searchDTO);
  }

  get(riskAssessmentId) {
    return this.getRequest(`/riskAssessment/${riskAssessmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/riskAssessment?field=${matchData}`, null);
  }

  addRiskAssessment(data) {
    return http.post("/riskAssessment/addRiskAssessment", data);
  }

  update(data) {
  	return http.post("/riskAssessment/updateRiskAssessment", data);
  }
  
  uploadImage(data,riskAssessmentId) {
  	return http.postForm("/riskAssessment/uploadImage/"+riskAssessmentId, data);
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

export default new RiskAssessmentService();
