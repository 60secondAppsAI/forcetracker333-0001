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
            <personnel-table
            v-if="personnels && personnels.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:personnels="personnels"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-personnels="getAllPersonnels"
             >

            </personnel-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import PersonnelTable from "@/components/PersonnelTable";
import PersonnelService from "../services/PersonnelService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PersonnelTable,
  },
  data() {
    return {
      personnels: [],
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
    async getAllPersonnels(sortBy='personnelId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PersonnelService.getAllPersonnels(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.personnels.length) {
					this.personnels = response.data.personnels;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching personnels:", error);
        }
        
      } catch (error) {
        console.error("Error fetching personnel details:", error);
      }
    },
  },
  mounted() {
    this.getAllPersonnels();
  },
  created() {
    this.$root.$on('searchQueryForPersonnelsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPersonnels();
    })
  }
};
</script>
<style></style>
