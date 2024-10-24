<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <asset-table
            v-if="assets && assets.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:assets="assets"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-assets="getAllAssets"
             >

            </asset-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import AssetTable from "@/components/AssetTable";
import AssetService from "../services/AssetService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AssetTable,
  },
  data() {
    return {
      assets: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllAssets(sortBy='assetId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AssetService.getAllAssets(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.assets.length) {
					this.assets = response.data.assets;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching assets:", error);
        }
        
      } catch (error) {
        console.error("Error fetching asset details:", error);
      }
    },
  },
  mounted() {
    this.getAllAssets();
  },
  created() {
    this.$root.$on('searchQueryForAssetsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAssets();
    })
  }
};
</script>
<style></style>
