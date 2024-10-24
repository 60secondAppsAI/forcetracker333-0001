import http from "../http-common";

class AssetService {
  getAllAssets(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/asset/assets`, searchDTO);
  }

  get(assetId) {
    return this.getRequest(`/asset/${assetId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/asset?field=${matchData}`, null);
  }

  addAsset(data) {
    return http.post("/asset/addAsset", data);
  }

  update(data) {
  	return http.post("/asset/updateAsset", data);
  }
  
  uploadImage(data,assetId) {
  	return http.postForm("/asset/uploadImage/"+assetId, data);
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

export default new AssetService();
