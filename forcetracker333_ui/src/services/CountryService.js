import http from "../http-common";

class CountryService {
  getAllCountrys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/country/countrys`, searchDTO);
  }

  get(countryId) {
    return this.getRequest(`/country/${countryId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/country?field=${matchData}`, null);
  }

  addCountry(data) {
    return http.post("/country/addCountry", data);
  }

  update(data) {
  	return http.post("/country/updateCountry", data);
  }
  
  uploadImage(data,countryId) {
  	return http.postForm("/country/uploadImage/"+countryId, data);
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

export default new CountryService();
