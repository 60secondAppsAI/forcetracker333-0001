<template>
	
	<!-- Main Sidebar -->
	<a-layout-sider
		collapsible
		class="sider-primary"
		breakpoint="lg"
		collapsed-width="0"
		width="250px"
		:collapsed="sidebarCollapsed"
		@collapse="$emit('toggleSidebar', ! sidebarCollapsed)"
		:trigger="null"
		:class="['ant-layout-sider-' + sidebarColor, 'ant-layout-sider-' + sidebarTheme]"
		theme="light"
		:style="{ backgroundColor: 'transparent',}">
			<div class="brand"><img src="images/drc-velocity.png" alt=""> <br><span>forcetracker333</span></div>
			<hr>

			<a-menu theme="light" mode="inline"
      			:open-keys="openKeys"
				@openChange="onOpenChange"
				>


	 
			
				<a-menu-item>
					<router-link to="/militaryUnits">
						<span class="label">Military Units</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/countrys">
						<span class="label">Countrys</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/soldiers">
						<span class="label">Soldiers</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/equipments">
						<span class="label">Equipments</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/operations">
						<span class="label">Operations</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/locations">
						<span class="label">Locations</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/movements">
						<span class="label">Movements</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/surveillances">
						<span class="label">Surveillances</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/commandCenters">
						<span class="label">Command Centers</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/intelligenceReports">
						<span class="label">Intelligence Reports</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/alerts">
						<span class="label">Alerts</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/missions">
						<span class="label">Missions</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/assets">
						<span class="label">Assets</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/riskAssessments">
						<span class="label">Risk Assessments</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/incidents">
						<span class="label">Incidents</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/tacticalMaps">
						<span class="label">Tactical Maps</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/personnels">
						<span class="label">Personnels</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/fleets">
						<span class="label">Fleets</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/resources">
						<span class="label">Resources</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/communications">
						<span class="label">Communications</span>
					</router-link>
				</a-menu-item>
		
	

			</a-menu>

	</a-layout-sider>

</template>

<script>

	export default ({
		props: {
			// Sidebar collapsed status.
			sidebarCollapsed: {
				type: Boolean,
				default: false,
			},
			
			// Main sidebar color.
			sidebarColor: {
				type: String,
				default: "primary",
			},
			
			// Main sidebar theme : light, white, dark.
			sidebarTheme: {
				type: String,
				default: "light",
			},
		},
		data() {
			return {
      			rootSubmenuKeys: [],
				openKeys: [],
			}
		},
		methods: {
			onOpenChange(openKeys)
			{
			
				const latestOpenKey = openKeys.find( key => this.openKeys.indexOf( key ) === -1) ;

				if ( this.rootSubmenuKeys.indexOf( latestOpenKey ) === -1 )
				{
					this.openKeys = openKeys;
				}
				else
				{
					this.openKeys = latestOpenKey ? [ latestOpenKey ] : [] ;
				}
			},
		},
	})

</script>

<style>

.drcv-label {
	padding-left: 65px;
}

</style>
