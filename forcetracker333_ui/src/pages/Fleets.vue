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
            <fleet-table
            v-if="fleets && fleets.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:fleets="fleets"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-fleets="getAllFleets"
             >

            </fleet-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import FleetTable from "@/components/FleetTable";
import FleetService from "../services/FleetService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FleetTable,
  },
  data() {
    return {
      fleets: [],
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
    async getAllFleets(sortBy='fleetId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FleetService.getAllFleets(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.fleets.length) {
					this.fleets = response.data.fleets;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching fleets:", error);
        }
        
      } catch (error) {
        console.error("Error fetching fleet details:", error);
      }
    },
  },
  mounted() {
    this.getAllFleets();
  },
  created() {
    this.$root.$on('searchQueryForFleetsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFleets();
    })
  }
};
</script>
<style></style>
