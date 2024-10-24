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
            <movement-table
            v-if="movements && movements.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:movements="movements"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-movements="getAllMovements"
             >

            </movement-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import MovementTable from "@/components/MovementTable";
import MovementService from "../services/MovementService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    MovementTable,
  },
  data() {
    return {
      movements: [],
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
    async getAllMovements(sortBy='movementId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await MovementService.getAllMovements(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.movements.length) {
					this.movements = response.data.movements;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching movements:", error);
        }
        
      } catch (error) {
        console.error("Error fetching movement details:", error);
      }
    },
  },
  mounted() {
    this.getAllMovements();
  },
  created() {
    this.$root.$on('searchQueryForMovementsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllMovements();
    })
  }
};
</script>
<style></style>
