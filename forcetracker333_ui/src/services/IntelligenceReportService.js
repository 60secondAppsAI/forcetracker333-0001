import http from "../http-common";

class IntelligenceReportService {
  getAllIntelligenceReports(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/intelligenceReport/intelligenceReports`, searchDTO);
  }

  get(intelligenceReportId) {
    return this.getRequest(`/intelligenceReport/${intelligenceReportId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/intelligenceReport?field=${matchData}`, null);
  }

  addIntelligenceReport(data) {
    return http.post("/intelligenceReport/addIntelligenceReport", data);
  }

  update(data) {
  	return http.post("/intelligenceReport/updateIntelligenceReport", data);
  }
  
  uploadImage(data,intelligenceReportId) {
  	return http.postForm("/intelligenceReport/uploadImage/"+intelligenceReportId, data);
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

export default new IntelligenceReportService();
