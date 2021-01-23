import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';

/**
 * `my-view`
 *
 * MyView element.
 *
 * @customElement
 * @polymer
 */
class MyView extends PolymerElement {

    static get template() {
        return html`
            <style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
        `;
    }

    static get is() {
        return 'my-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(MyView.is, MyView);
