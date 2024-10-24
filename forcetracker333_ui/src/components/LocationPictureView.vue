<template>



    <div>
        <div class="row card-container">
            <div class="col location-card" v-for="location in locations" :key="location.locationId">
                <img class="location-image" :alt="location.locationNumber" :src="`/images/${location.imageFile}`" />
                <!-- <img class="location-image" :alt="location.locationNumber"
                    :src="`https://source.unsplash.com/collection/928423/230x250`" /> -->
                <div class="location-info">
                    <div class="location-details">
                        <p> Location: <a :href="'#location/' +  location.locationId"  target="_blank" class="location-link">{{ location.locationNumber }}</a></p>
                        <p>{{ location.cityState }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>


</template>
  
<script>

export default {
    components: {
    },
    props: {
        locations: Array,
    },

    methods: {

    },
};
</script>
  
<style scoped>
.card-container {
    display: flex;
    column-gap: 15px;
    flex-wrap: wrap;
    margin: 5px;

}

.card {
    max-width: 230px;

}

.location-picture-view {
    margin-bottom: 15px;
    /* Separate each row from the content below */
}

.location-card {
    max-width: 250px;
    min-width: 250px;
    border-radius: 30px;
    padding: 0px;
    margin-bottom: 25px;
    /* Separate each card */
}

.location-image {
				    	width: 250px;
	    	height: 200px;
	    	        	border-radius: 4px;
    }

.location-info {
    margin-top: 0px;
}

.location-name {
    font-weight: bold;
    font-size: 16px;
    margin-bottom: 2px;
    line-height: 1.2;
}

.location-details p {
    margin-top: 0px;
    text-align: left;
}

.location-link {
    display: inline;
    margin-top: 5px;
    color: #1890ff;
}
</style>
  