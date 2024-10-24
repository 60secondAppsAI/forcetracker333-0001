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
            <location-table
            v-if="locations && locations.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:locations="locations"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-locations="getAllLocations"
             >

            </location-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import LocationTable from "@/components/LocationTable";
import LocationService from "../services/LocationService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    LocationTable,
  },
  data() {
    return {
      locations: [],
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
    async getAllLocations(sortBy='locationId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await LocationService.getAllLocations(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.locations.length) {
					this.locations = response.data.locations;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching locations:", error);
        }
        
      } catch (error) {
        console.error("Error fetching location details:", error);
      }
    },
  },
  mounted() {
    this.getAllLocations();
  },
  created() {
    this.$root.$on('searchQueryForLocationsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllLocations();
    })
  }
};
</script>
<style></style>
