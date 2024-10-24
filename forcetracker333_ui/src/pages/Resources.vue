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
            <resource-table
            v-if="resources && resources.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:resources="resources"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-resources="getAllResources"
             >

            </resource-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import ResourceTable from "@/components/ResourceTable";
import ResourceService from "../services/ResourceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ResourceTable,
  },
  data() {
    return {
      resources: [],
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
    async getAllResources(sortBy='resourceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ResourceService.getAllResources(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.resources.length) {
					this.resources = response.data.resources;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching resources:", error);
        }
        
      } catch (error) {
        console.error("Error fetching resource details:", error);
      }
    },
  },
  mounted() {
    this.getAllResources();
  },
  created() {
    this.$root.$on('searchQueryForResourcesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllResources();
    })
  }
};
</script>
<style></style>
