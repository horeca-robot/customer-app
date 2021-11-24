import { shallowMount, createLocalVue } from "@vue/test-utils";
import App from "@/App.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

const localVue = createLocalVue();  
localVue.use(BootstrapVue);
localVue.use(IconsPlugin);

describe("App.vue", () => {
    it("Test the gethelp method.", async () => {
        const wrapper = shallowMount(App, {
            mocks: {
                $route: "/help"
            },
            stubs: ['router-link', 'router-view'],
            localVue,
        });
        await wrapper.trigger('click', { button: 0 });
        expect(wrapper.vm.$route).toBe("/help");
    });

    it("Test the gotocart method.", async () => {
        const wrapper = shallowMount(App, {
            mocks: {
                $route: "/bestelling"
            },
            stubs: ['router-link', 'router-view'],
            localVue,
        });
        await wrapper.trigger('click', { button: 1 });
        expect(wrapper.vm.$route).toBe("/bestelling");
    });
});