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
            <country-table
            v-if="countrys && countrys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:countrys="countrys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-countrys="getAllCountrys"
             >

            </country-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import CountryTable from "@/components/CountryTable";
import CountryService from "../services/CountryService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CountryTable,
  },
  data() {
    return {
      countrys: [],
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
    async getAllCountrys(sortBy='countryId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CountryService.getAllCountrys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.countrys.length) {
					this.countrys = response.data.countrys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching countrys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching country details:", error);
      }
    },
  },
  mounted() {
    this.getAllCountrys();
  },
  created() {
    this.$root.$on('searchQueryForCountrysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCountrys();
    })
  }
};
</script>
<style></style>
