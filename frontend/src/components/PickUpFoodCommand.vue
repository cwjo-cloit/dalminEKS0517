<template>

    <v-card outlined>
        <v-card-title>
            PickUpFood
        </v-card-title>

        <v-card-text>
            <Number label="RiderId" v-model="value.riderId" :editMode="editMode"/>
            <String label="RiderName" v-model="value.riderName" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="pickUpFood"
            >
                PickUpFood
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'PickUpFoodCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.riderId = 0;
            this.value.riderName = '';
        },
        watch: {
        },
        methods: {
            pickUpFood() {
                this.$emit('pickUpFood', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

