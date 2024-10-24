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
            <militaryUnit-table
            v-if="militaryUnits && militaryUnits.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:militaryUnits="militaryUnits"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-military-units="getAllMilitaryUnits"
             >

            </militaryUnit-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import MilitaryUnitTable from "@/components/MilitaryUnitTable";
import MilitaryUnitService from "../services/MilitaryUnitService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MilitaryUnitTable,
  },
  data() {
    return {
      militaryUnits: [],
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
    async getAllMilitaryUnits(sortBy='militaryUnitId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MilitaryUnitService.getAllMilitaryUnits(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.militaryUnits.length) {
					this.militaryUnits = response.data.militaryUnits;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching militaryUnits:", error);
        }
        
      } catch (error) {
        console.error("Error fetching militaryUnit details:", error);
      }
    },
  },
  mounted() {
    this.getAllMilitaryUnits();
  },
  created() {
    this.$root.$on('searchQueryForMilitaryUnitsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMilitaryUnits();
    })
  }
};
</script>
<style></style>
