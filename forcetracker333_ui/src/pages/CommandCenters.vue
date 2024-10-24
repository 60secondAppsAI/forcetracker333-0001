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
            <commandCenter-table
            v-if="commandCenters && commandCenters.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:commandCenters="commandCenters"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-command-centers="getAllCommandCenters"
             >

            </commandCenter-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import CommandCenterTable from "@/components/CommandCenterTable";
import CommandCenterService from "../services/CommandCenterService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CommandCenterTable,
  },
  data() {
    return {
      commandCenters: [],
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
    async getAllCommandCenters(sortBy='commandCenterId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CommandCenterService.getAllCommandCenters(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.commandCenters.length) {
					this.commandCenters = response.data.commandCenters;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching commandCenters:", error);
        }
        
      } catch (error) {
        console.error("Error fetching commandCenter details:", error);
      }
    },
  },
  mounted() {
    this.getAllCommandCenters();
  },
  created() {
    this.$root.$on('searchQueryForCommandCentersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCommandCenters();
    })
  }
};
</script>
<style></style>
