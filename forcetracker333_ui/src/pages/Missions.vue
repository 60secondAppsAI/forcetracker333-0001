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
            <mission-table
            v-if="missions && missions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:missions="missions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-missions="getAllMissions"
             >

            </mission-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import MissionTable from "@/components/MissionTable";
import MissionService from "../services/MissionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MissionTable,
  },
  data() {
    return {
      missions: [],
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
    async getAllMissions(sortBy='missionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MissionService.getAllMissions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.missions.length) {
					this.missions = response.data.missions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching missions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching mission details:", error);
      }
    },
  },
  mounted() {
    this.getAllMissions();
  },
  created() {
    this.$root.$on('searchQueryForMissionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMissions();
    })
  }
};
</script>
<style></style>
