import { shallowMount } from "@vue/test-utils";
import App from "@/App.vue";

describe("App.vue", () => {
    it("Test the gethelp method.", async () => {
        const wrapper = shallowMount(App, {
            mocks: {
                $route: "/help"
            }
        });
        await wrapper.trigger('click', { button: 0 });
        expect(wrapper.vm.$route).toBe("/help");
    });

    it("Test the gotocart method.", async () => {
        const wrapper = shallowMount(App, {
            mocks: {
                $route: "/cart"
            }
        });
        await wrapper.trigger('click', { button: 1 });
        expect(wrapper.vm.$route).toBe("/cart");
    });
});