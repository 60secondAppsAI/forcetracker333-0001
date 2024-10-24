
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3">
      <div class="col-8"></div>
      <div class="col-4">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Fleets</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalFleets = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalFleets">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Fleet</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="FleetId" type="text" placeholder="Enter FleetId" v-model="fleetToAdd.fleetId"></base-input>
  <base-input label="Name" type="text" placeholder="Enter Name" v-model="fleetToAdd.name"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="fleets" :row-key="record => record.FleetId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <FleetPictureView :fleets="fleets" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/Inputs/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/index";
import FleetService from "../services/FleetService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import FleetPictureView from './FleetPictureView.vue';


const fleetsColumns = [
  "fleetId",
  "year",
  "date",
  "competitionId",
  "fleetId"
]

export default {
  props: {
    fleets: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    FleetPictureView
  },

  data() {
    return {
      modalFleets: false,
        isTableView: true,

      columns: [
        {
          title: 'Fleet Id',
		dataIndex: 'fleetId',
          visible: true,
          scopedSlots: { customRender: 'fleetId' },
          sorter: true
          //sorter: (a, b) => a.fleetId - b.fleetId,
          //sorter: (a, b) => a.fleetId.localeCompare(b.fleetId),
        },
        {
          title: 'Name',
		dataIndex: 'name',
          visible: true,
          scopedSlots: { customRender: 'name' },
          sorter: true
          //sorter: (a, b) => a.name - b.name,
          //sorter: (a, b) => a.name.localeCompare(b.name),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} fleets`,
      },

      fleets: [],
      fleetToAdd: {},

      fleetsTable: {
        columns: [...fleetsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'fleetId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderFleetsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let fleetsTableData = [];
      for (let i = 0; i < this.fleets.length; i++) {
        fleetsTableData.push({
          id: i,
          fleetId: this.fleets[i].fleetId,
          year: this.fleets[i].year,
          date: this.fleets[i].date,
          competitionId: this.fleets[i].competitionId,
          fleetId: this.fleets[i].fleetId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-fleets',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToMilitaryUnitDetail(id) {
      this.$router.push({ name: 'MilitaryUnitDetail', params: { militaryUnitId: id.toString() }})
    },
    routingToCountryDetail(id) {
      this.$router.push({ name: 'CountryDetail', params: { countryId: id.toString() }})
    },
    routingToSoldierDetail(id) {
      this.$router.push({ name: 'SoldierDetail', params: { soldierId: id.toString() }})
    },
    routingToEquipmentDetail(id) {
      this.$router.push({ name: 'EquipmentDetail', params: { equipmentId: id.toString() }})
    },
    routingToOperationDetail(id) {
      this.$router.push({ name: 'OperationDetail', params: { operationId: id.toString() }})
    },
    routingToLocationDetail(id) {
      this.$router.push({ name: 'LocationDetail', params: { locationId: id.toString() }})
    },
    routingToMovementDetail(id) {
      this.$router.push({ name: 'MovementDetail', params: { movementId: id.toString() }})
    },
    routingToSurveillanceDetail(id) {
      this.$router.push({ name: 'SurveillanceDetail', params: { surveillanceId: id.toString() }})
    },
    routingToCommandCenterDetail(id) {
      this.$router.push({ name: 'CommandCenterDetail', params: { commandCenterId: id.toString() }})
    },
    routingToIntelligenceReportDetail(id) {
      this.$router.push({ name: 'IntelligenceReportDetail', params: { intelligenceReportId: id.toString() }})
    },
    routingToAlertDetail(id) {
      this.$router.push({ name: 'AlertDetail', params: { alertId: id.toString() }})
    },
    routingToMissionDetail(id) {
      this.$router.push({ name: 'MissionDetail', params: { missionId: id.toString() }})
    },
    routingToAssetDetail(id) {
      this.$router.push({ name: 'AssetDetail', params: { assetId: id.toString() }})
    },
    routingToRiskAssessmentDetail(id) {
      this.$router.push({ name: 'RiskAssessmentDetail', params: { riskAssessmentId: id.toString() }})
    },
    routingToIncidentDetail(id) {
      this.$router.push({ name: 'IncidentDetail', params: { incidentId: id.toString() }})
    },
    routingToTacticalMapDetail(id) {
      this.$router.push({ name: 'TacticalMapDetail', params: { tacticalMapId: id.toString() }})
    },
    routingToPersonnelDetail(id) {
      this.$router.push({ name: 'PersonnelDetail', params: { personnelId: id.toString() }})
    },
    routingToFleetDetail(id) {
      this.$router.push({ name: 'FleetDetail', params: { fleetId: id.toString() }})
    },
    routingToResourceDetail(id) {
      this.$router.push({ name: 'ResourceDetail', params: { resourceId: id.toString() }})
    },
    routingToCommunicationDetail(id) {
      this.$router.push({ name: 'CommunicationDetail', params: { communicationId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForFleetsChanged', this.searchQuery);
		//this.renderFleetsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalFleets = false;

      const currentDate = new Date().getTime();
      this.fleetToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.fleetToAdd);
      console.log(jsonData);
      
      const res = await FleetService.addFleet(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderFleetsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}

</style>
