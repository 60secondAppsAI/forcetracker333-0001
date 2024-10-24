import Vue from 'vue'
import VueRouter from 'vue-router'
import MilitaryUnits from  '@/pages/MilitaryUnits.vue';
import MilitaryUnitDetail from  '@/pages/MilitaryUnitDetail.vue';
import Countrys from  '@/pages/Countrys.vue';
import CountryDetail from  '@/pages/CountryDetail.vue';
import Soldiers from  '@/pages/Soldiers.vue';
import SoldierDetail from  '@/pages/SoldierDetail.vue';
import Equipments from  '@/pages/Equipments.vue';
import EquipmentDetail from  '@/pages/EquipmentDetail.vue';
import Operations from  '@/pages/Operations.vue';
import OperationDetail from  '@/pages/OperationDetail.vue';
import Locations from  '@/pages/Locations.vue';
import LocationDetail from  '@/pages/LocationDetail.vue';
import Movements from  '@/pages/Movements.vue';
import MovementDetail from  '@/pages/MovementDetail.vue';
import Surveillances from  '@/pages/Surveillances.vue';
import SurveillanceDetail from  '@/pages/SurveillanceDetail.vue';
import CommandCenters from  '@/pages/CommandCenters.vue';
import CommandCenterDetail from  '@/pages/CommandCenterDetail.vue';
import IntelligenceReports from  '@/pages/IntelligenceReports.vue';
import IntelligenceReportDetail from  '@/pages/IntelligenceReportDetail.vue';
import Alerts from  '@/pages/Alerts.vue';
import AlertDetail from  '@/pages/AlertDetail.vue';
import Missions from  '@/pages/Missions.vue';
import MissionDetail from  '@/pages/MissionDetail.vue';
import Assets from  '@/pages/Assets.vue';
import AssetDetail from  '@/pages/AssetDetail.vue';
import RiskAssessments from  '@/pages/RiskAssessments.vue';
import RiskAssessmentDetail from  '@/pages/RiskAssessmentDetail.vue';
import Incidents from  '@/pages/Incidents.vue';
import IncidentDetail from  '@/pages/IncidentDetail.vue';
import TacticalMaps from  '@/pages/TacticalMaps.vue';
import TacticalMapDetail from  '@/pages/TacticalMapDetail.vue';
import Personnels from  '@/pages/Personnels.vue';
import PersonnelDetail from  '@/pages/PersonnelDetail.vue';
import Fleets from  '@/pages/Fleets.vue';
import FleetDetail from  '@/pages/FleetDetail.vue';
import Resources from  '@/pages/Resources.vue';
import ResourceDetail from  '@/pages/ResourceDetail.vue';
import Communications from  '@/pages/Communications.vue';
import CommunicationDetail from  '@/pages/CommunicationDetail.vue';

Vue.use(VueRouter)

let routes = [
	{
		// will match everything
		path: '*',
		component: () => import('../views/404.vue'),
	},
	{
		path: '/',
		name: 'Home',
			redirect: '/militaryUnits',
																					},
	{
		path: '/dashboard',
		name: 'Dashboard',
		layout: "dashboard",
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () => import(/* webpackChunkName: "dashboard" */ '../views/Dashboard.vue'),
	},
	{
		path: '/layout',
		name: 'Layout',
		layout: "dashboard",
		component: () => import('../views/Layout.vue'),
	},
	{
		path: '/militaryUnits',
		name: 'MilitaryUnits',
		layout: "dashboard",
		component: MilitaryUnits,
	},
	{
	    path: '/militaryUnit/:militaryUnitId', 
	    name: 'MilitaryUnitDetail',
		layout: "dashboard",
	    component: MilitaryUnitDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/countrys',
		name: 'Countrys',
		layout: "dashboard",
		component: Countrys,
	},
	{
	    path: '/country/:countryId', 
	    name: 'CountryDetail',
		layout: "dashboard",
	    component: CountryDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/soldiers',
		name: 'Soldiers',
		layout: "dashboard",
		component: Soldiers,
	},
	{
	    path: '/soldier/:soldierId', 
	    name: 'SoldierDetail',
		layout: "dashboard",
	    component: SoldierDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/equipments',
		name: 'Equipments',
		layout: "dashboard",
		component: Equipments,
	},
	{
	    path: '/equipment/:equipmentId', 
	    name: 'EquipmentDetail',
		layout: "dashboard",
	    component: EquipmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/operations',
		name: 'Operations',
		layout: "dashboard",
		component: Operations,
	},
	{
	    path: '/operation/:operationId', 
	    name: 'OperationDetail',
		layout: "dashboard",
	    component: OperationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/locations',
		name: 'Locations',
		layout: "dashboard",
		component: Locations,
	},
	{
	    path: '/location/:locationId', 
	    name: 'LocationDetail',
		layout: "dashboard",
	    component: LocationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/movements',
		name: 'Movements',
		layout: "dashboard",
		component: Movements,
	},
	{
	    path: '/movement/:movementId', 
	    name: 'MovementDetail',
		layout: "dashboard",
	    component: MovementDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/surveillances',
		name: 'Surveillances',
		layout: "dashboard",
		component: Surveillances,
	},
	{
	    path: '/surveillance/:surveillanceId', 
	    name: 'SurveillanceDetail',
		layout: "dashboard",
	    component: SurveillanceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/commandCenters',
		name: 'CommandCenters',
		layout: "dashboard",
		component: CommandCenters,
	},
	{
	    path: '/commandCenter/:commandCenterId', 
	    name: 'CommandCenterDetail',
		layout: "dashboard",
	    component: CommandCenterDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/intelligenceReports',
		name: 'IntelligenceReports',
		layout: "dashboard",
		component: IntelligenceReports,
	},
	{
	    path: '/intelligenceReport/:intelligenceReportId', 
	    name: 'IntelligenceReportDetail',
		layout: "dashboard",
	    component: IntelligenceReportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/alerts',
		name: 'Alerts',
		layout: "dashboard",
		component: Alerts,
	},
	{
	    path: '/alert/:alertId', 
	    name: 'AlertDetail',
		layout: "dashboard",
	    component: AlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/missions',
		name: 'Missions',
		layout: "dashboard",
		component: Missions,
	},
	{
	    path: '/mission/:missionId', 
	    name: 'MissionDetail',
		layout: "dashboard",
	    component: MissionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/assets',
		name: 'Assets',
		layout: "dashboard",
		component: Assets,
	},
	{
	    path: '/asset/:assetId', 
	    name: 'AssetDetail',
		layout: "dashboard",
	    component: AssetDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/riskAssessments',
		name: 'RiskAssessments',
		layout: "dashboard",
		component: RiskAssessments,
	},
	{
	    path: '/riskAssessment/:riskAssessmentId', 
	    name: 'RiskAssessmentDetail',
		layout: "dashboard",
	    component: RiskAssessmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/incidents',
		name: 'Incidents',
		layout: "dashboard",
		component: Incidents,
	},
	{
	    path: '/incident/:incidentId', 
	    name: 'IncidentDetail',
		layout: "dashboard",
	    component: IncidentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tacticalMaps',
		name: 'TacticalMaps',
		layout: "dashboard",
		component: TacticalMaps,
	},
	{
	    path: '/tacticalMap/:tacticalMapId', 
	    name: 'TacticalMapDetail',
		layout: "dashboard",
	    component: TacticalMapDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/personnels',
		name: 'Personnels',
		layout: "dashboard",
		component: Personnels,
	},
	{
	    path: '/personnel/:personnelId', 
	    name: 'PersonnelDetail',
		layout: "dashboard",
	    component: PersonnelDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/fleets',
		name: 'Fleets',
		layout: "dashboard",
		component: Fleets,
	},
	{
	    path: '/fleet/:fleetId', 
	    name: 'FleetDetail',
		layout: "dashboard",
	    component: FleetDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/resources',
		name: 'Resources',
		layout: "dashboard",
		component: Resources,
	},
	{
	    path: '/resource/:resourceId', 
	    name: 'ResourceDetail',
		layout: "dashboard",
	    component: ResourceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/communications',
		name: 'Communications',
		layout: "dashboard",
		component: Communications,
	},
	{
	    path: '/communication/:communicationId', 
	    name: 'CommunicationDetail',
		layout: "dashboard",
	    component: CommunicationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/requests/quickadd',
		name: 'QuickAdd',
		layout: "dashboard",
		meta: {
			title: 'quickadd',
			sidebarMap: ['applications'],
			breadcrumbs: ['Requests', 'QuickAdd'],
		},
		component: () => import('../pages/QuickAdd.vue'),
	},
	{
		path: '/tables',
		name: 'Tables',
		layout: "dashboard",
		component: () => import('../views/Tables.vue'),
	},
	{
		path: '/billing',
		name: 'Billing',
		layout: "dashboard",
		component: () => import('../views/Billing.vue'),
	},
	{
		path: '/rtl',
		name: 'RTL',
		layout: "dashboard-rtl",
		meta: {
			layoutClass: 'dashboard-rtl',
		},
		component: () => import('../views/RTL.vue'),
	},
	{
		path: '/Profile',
		name: 'Profile',
		layout: "dashboard",
		meta: {
			layoutClass: 'layout-profile',
		},
		component: () => import('../views/Profile.vue'),
	},
	{
		path: '/sign-in',
		name: 'Sign-In',
		component: () => import('../views/Sign-In.vue'),
	},
	{
		path: '/sign-up',
		name: 'Sign-Up',
		meta: {
			layoutClass: 'layout-sign-up',
		},
		component: () => import('../views/Sign-Up.vue'),
	},
]

// Adding layout property from each route to the meta
// object so it can be accessed later.
function addLayoutToRoute( route, parentLayout = "default" )
{
	route.meta = route.meta || {} ;
	route.meta.layout = route.layout || parentLayout ;
	
	if( route.children )
	{
		route.children = route.children.map( ( childRoute ) => addLayoutToRoute( childRoute, route.meta.layout ) ) ;
	}
	return route ;
}

routes = routes.map( ( route ) => addLayoutToRoute( route ) ) ;

const router = new VueRouter({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes,
	scrollBehavior (to, from, savedPosition) {
		if ( to.hash ) {
			return {
				selector: to.hash,
				behavior: 'smooth',
			}
		}
		return {
			x: 0,
			y: 0,
			behavior: 'smooth',
		}
	}
})

export default router
