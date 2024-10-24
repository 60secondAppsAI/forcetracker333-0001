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
            <riskAssessment-table
            v-if="riskAssessments && riskAssessments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:riskAssessments="riskAssessments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-risk-assessments="getAllRiskAssessments"
             >

            </riskAssessment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import RiskAssessmentTable from "@/components/RiskAssessmentTable";
import RiskAssessmentService from "../services/RiskAssessmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RiskAssessmentTable,
  },
  data() {
    return {
      riskAssessments: [],
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
    async getAllRiskAssessments(sortBy='riskAssessmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RiskAssessmentService.getAllRiskAssessments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.riskAssessments.length) {
					this.riskAssessments = response.data.riskAssessments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching riskAssessments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching riskAssessment details:", error);
      }
    },
  },
  mounted() {
    this.getAllRiskAssessments();
  },
  created() {
    this.$root.$on('searchQueryForRiskAssessmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRiskAssessments();
    })
  }
};
</script>
<style></style>
