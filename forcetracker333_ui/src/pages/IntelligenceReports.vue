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
            <intelligenceReport-table
            v-if="intelligenceReports && intelligenceReports.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:intelligenceReports="intelligenceReports"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-intelligence-reports="getAllIntelligenceReports"
             >

            </intelligenceReport-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import IntelligenceReportTable from "@/components/IntelligenceReportTable";
import IntelligenceReportService from "../services/IntelligenceReportService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    IntelligenceReportTable,
  },
  data() {
    return {
      intelligenceReports: [],
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
    async getAllIntelligenceReports(sortBy='intelligenceReportId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await IntelligenceReportService.getAllIntelligenceReports(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.intelligenceReports.length) {
					this.intelligenceReports = response.data.intelligenceReports;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching intelligenceReports:", error);
        }
        
      } catch (error) {
        console.error("Error fetching intelligenceReport details:", error);
      }
    },
  },
  mounted() {
    this.getAllIntelligenceReports();
  },
  created() {
    this.$root.$on('searchQueryForIntelligenceReportsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllIntelligenceReports();
    })
  }
};
</script>
<style></style>
